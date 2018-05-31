package com.nsb.practice.designpatterns.decorators;

import java.util.Date;

public class FileDateDecorator extends FileDecorator{

    public FileDateDecorator(TemplateFile templateFile) {
        super(templateFile);
    }

    public StringBuilder getContent() {
        return templateFile.getContent();
    }

    public void fillContent() {
        //先用上一个装饰器处理，再用自己的逻辑处理
        this.templateFile.fillContent();
        
        StringBuilder content = this.templateFile.getContent();
        content.append("\r\nDate: " + new Date(System.currentTimeMillis()));
    }

}
