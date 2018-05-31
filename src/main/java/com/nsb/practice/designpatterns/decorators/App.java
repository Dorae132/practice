package com.nsb.practice.designpatterns.decorators;

public class App {
    public static void main(String[] args) {
        TemplateFile file = new ImportTemplateFile();

        // 把要装饰的对象传给装饰器
        TemplateFile dateDecorator = new FileDateDecorator(file);

        /**
         * 这里可以有两种做法。
         * 第一种是先调用dateDecorator.fillContent()，先进行装饰，然后再把file传给autherDecorator
         * ，由它继续装饰。 第二种是把dateDecorator作为参数传给autherDecorator，由后者一次性地全部装饰。
         * 这里采用第二种。选用这种，要保证在装饰器的装饰方法里面，显式地调用传入参数的装饰方法。
         */
        TemplateFile autherDecorator = new FileAutherDecorator(dateDecorator);

        autherDecorator.fillContent();

        System.out.println(autherDecorator.getContent());
    }
}
