package com.ferra13671.jsonutils.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
@Setter
@AllArgsConstructor
public abstract class ConfigFile {
    private Path defaultPath;

    public ConfigFile() {
        this(null);
    }

    public void load() {
        if (this.defaultPath != null)
            load(this.defaultPath);

        new UnsupportedOperationException("Default path is null").printStackTrace();
    }

    public void load(Path path) {
        try(InputStream inputStream = Files.newInputStream(path)) {
            load(inputStream);
        } catch (Exception e) {
            new UnsupportedOperationException("Cannot load config'" + path + "'. Reason: " + e.getMessage()).printStackTrace();
        }
    }

    public abstract void load(InputStream inputStream) throws Exception;

    public void save() {
        if (this.defaultPath != null)
            save(this.defaultPath);

        new UnsupportedOperationException("Default path is null").printStackTrace();
    }

    public void save(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            save(outputStream);
        } catch (Exception e) {
            new UnsupportedOperationException("Cannot save config'" + path + "'. Reason: " + e.getMessage()).printStackTrace();
        }
    }

    public abstract void save(OutputStream outputStream) throws Exception;
}
