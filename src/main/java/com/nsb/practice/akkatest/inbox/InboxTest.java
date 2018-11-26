package com.nsb.practice.akkatest.inbox;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

public class InboxTest extends UntypedActor {

    public enum Msg{
        WORKING, DONE, CLOSE;
    }

    @Override
    public void onReceive(Object o) {
        if(o == Msg.WORKING){
            System.out.println("i am working.");
        }else if(o == Msg.DONE){
            System.out.println("i am done");
        }else if(o == Msg.CLOSE){
            System.out.println("i am close.");
            getSender().tell(Msg.CLOSE, getSelf());//告诉消息发送者我要关闭了。
            getContext().stop(getSelf());//关闭自己
        }else{
            unhandled(o);
        }
    }

    public static void main(String [] args){
        ActorSystem system = ActorSystem.create("inbox", ConfigFactory.load("akka.conf"));
        ActorRef inboxTest = system.actorOf(Props.create(InboxTest.class), "InboxTest");

        Inbox inbox = Inbox.create(system);
        inbox.watch(inboxTest);//监听一个actor

        //通过inbox来发送消息
        inbox.send(inboxTest, Msg.WORKING);
        inbox.send(inboxTest, Msg.DONE);
        inbox.send(inboxTest, Msg.CLOSE);

        while(true){
            try {
                Object receive = inbox.receive(Duration.create(10, TimeUnit.SECONDS));
                if(receive == Msg.CLOSE){//收到的inbox的消息
                    System.out.println("inboxTextActor is closing");
                }else if(receive instanceof  Terminated){//中断 ，和线程一个概念
                    System.out.println("inboxTextActor is closed");
                    system.shutdown();
                    break;
                }else {
                    System.out.println(receive);
                }
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
