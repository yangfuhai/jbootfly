package io.jboot.fly;

import io.jboot.codegen.model.JbootModelGenerator;

/**
 * Model 的代码生成器
 */
public class ModelGen {

    public static void main(String[] args) {

        String modelPackage = "io.jboot.fly.model";

        JbootModelGenerator.run(modelPackage);
    }
}
