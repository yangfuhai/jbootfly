package io.jboot.fly;

import io.jboot.codegen.service.JbootServiceGenerator;

/**
 * Service 层的代码生成器
 */
public class ServiceGen {

    public static void main(String[] args) {

        String basePackage = "io.jboot.fly.service";
        String modelPackage = "io.jboot.fly.model";

        JbootServiceGenerator.run(basePackage, modelPackage);
    }
}
