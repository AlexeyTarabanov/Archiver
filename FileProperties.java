package com.javarush.task.task31.task3110;

// будет отвечать за свойства каждого файла в архиве
// свойства - это набор, состоящий из:
// имя файла, размер файла до и после сжатия, метод сжатия
public class FileProperties {

    private String name;
    // размер в байтах
    private long size;
    // размер после сжатия в байтах
    private long compressedSize;
    // метод сжатия
    private int compressionMethod;

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public int getCompressionMethod() {
        return compressionMethod;
    }

    public FileProperties(String name, long size, long compressedSize, int compressionMethod) {
        this.name = name;
        this.size = size;
        this.compressedSize = compressedSize;
        this.compressionMethod = compressionMethod;
    }

    // будет считать степень сжатия
    public long getCompressionRatio() {
        return 100 - ((compressedSize * 100) / size);
    }

    @Override
    public String toString() {
        if (size > 0) {
            return name + " " + size/1024 + " Kb (" + compressedSize/1024 + " Kb) сжатие: " + getCompressionRatio() + "%";
            // нулевой размер может быть, например, у директории
        } else
            return name;
    }
}
