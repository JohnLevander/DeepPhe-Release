package edu.pitt.dbmi.deepphe;

import org.apache.ctakes.core.pipeline.PiperFileReader;
import org.apache.ctakes.core.pipeline.PiperFileRunner;
import org.apache.uima.UIMAException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IntegrationTest {

    private static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @DisplayName("Test Pipeline")
    @Test
    public void testPipeline() {
        try {
            final java.util.List<String> args = new ArrayList<>();
            args.add(0, "-p");
            args.add(1, "data/pipeline/DeepPhe.piper");
            args.add(2, "-c");
            args.add(3, "data/pipeline/PatientX.piper_cli");
            args.add(4, "-i");
            args.add(5, "data/sample/reports");
            args.add(6, "-o");
            args.add(7, "../../output/sample/reports");
            PiperFileRunner.run(args.toArray(new String[args.size()]));
            File outputFile = new File("/Users/johnlevander/output");
            //other checks:
            //  1. did it write the .db directory
            //  2. Sean: "What is the best way
            System.out.println(outputFile.exists());
            assertEquals(outputFile.exists(), true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
