package com.example.demo;

import com.example.demo.model.Employee;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Session session = new Configuration()
                .configure().buildSessionFactory().openSession();
        session.beginTransaction();

//        Developer developer = new Developer();
//        developer.setName("Alex");
//        developer.setProgrammingLanguage(ProgrammingLanguage.JAVA);
//        session.save(developer);
//
//        Manager manager = new Manager();
//        manager.setName("Max");
//        manager.setNumberOfIdleHours(17);
//        session.save(manager);
//
//        Skill skill1 = new Skill("communicative");
//        skill1.setEmployee(developer);
//        Skill skill2 = new Skill("flexibility");
//        skill2.setEmployee(developer);
//        Skill skill3 = new Skill("persuasiveness");
//        skill3.setEmployee(manager);
//        Skill skill4 = new Skill("determination");
//        skill4.setEmployee(manager);
//        session.save(skill1);
//        session.save(skill2);
//        session.save(skill3);
//        session.save(skill4);


        session.createQuery("Select s.employee from Skill s where s.skillName = :skillName", Employee.class)
                .setParameter("skillName", "flexibility")
                .getResultList()
                .forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }

}
