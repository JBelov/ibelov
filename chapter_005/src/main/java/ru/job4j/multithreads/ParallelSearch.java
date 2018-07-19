package ru.job4j.multithreads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static java.nio.file.FileVisitResult.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    private volatile boolean finish = false;
    private Thread read;
    @GuardedBy("this")
    private final LinkedBlockingQueue<String> files = new LinkedBlockingQueue<>();
    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void init() {
        Thread search = new Thread(() -> {
            for (String ext : exts) {
                try {
                    Files.walkFileTree(Paths.get(root), new FilesFinder(ext));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish = true;
            }
        });
        search.start();
        read = new Thread(() -> {
            while (!finish) {
                synchronized (ParallelSearch.this) {
                    try {
                        String filePath = files.poll();
                        if (filePath != null) {
                            String file = new String(Files.readAllBytes(Paths.get(filePath)));
                            if (file.contains(text)) {
                                paths.add(filePath);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        read.start();
    }

    public List<String> result() {
        try {
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.paths;
    }

    public class FilesFinder extends SimpleFileVisitor<Path> {
        private String ext;

        FilesFinder(String ext) {
            this.ext = ext;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            synchronized (ParallelSearch.this) {
                if (file.toString().endsWith(ext)) {
                    files.offer(file.toString());
                    System.out.println(file.toString());
                }
            }
            return CONTINUE;
        }
    }
}