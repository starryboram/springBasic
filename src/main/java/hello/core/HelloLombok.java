package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // lombok에서 @getter, @setter를 이용해서 편리하게 개발할 수 있다.
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("setName");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
