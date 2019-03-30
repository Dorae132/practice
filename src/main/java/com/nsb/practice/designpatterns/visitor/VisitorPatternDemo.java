package com.nsb.practice.designpatterns.visitor;

/**
 * http://www.runoob.com/design-pattern/visitor-pattern.html
 * @author Dorae
 *
 */
public class VisitorPatternDemo {
    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
