package hr.tel.fer.ilj.lab.lab1.logging;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Go through all files in folder.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class FileVisitor extends SimpleFileVisitor<Path> {
    List<LogEntry> allLogs = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Reader reader = new FileReader(file.toFile());
        List<LogEntry> fileLog;
        if (attrs.isRegularFile()) {
            fileLog = LogLoader.load(reader);
            allLogs.addAll(fileLog);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        throw new IOException("Can't read file " + file.getFileName(), exc);

    }

    /**
     * AllLogs getter.
     *
     * @return Logs from all files visited.
     */
    public List<LogEntry> getAllLogs() {
        return allLogs;
    }
}
