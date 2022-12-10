package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class SpriteFramesDescriptionGenerator {

    public static void main(String[] args) {
        if (args.length == 1)
            process(args[0]);
        else
            usage();
    }

    private static void process(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
            File targetDesc = Path.of(file.getAbsolutePath(), file.getName() + ".ani").toFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetDesc))) {
                if (files != null)
                    for (int i = 0; i < files.length; i++)
                        writer.write(i + ": " + files[i].getName() + "\n");

                writer.flush();

                System.out.println("Generated!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not a folder");
        }
    }

    private static void usage() {
        System.out.println(SpriteFramesDescriptionGenerator.class.getSimpleName() + " directory");
        System.out.println("Only support *.jpg | *.png");
    }
}
