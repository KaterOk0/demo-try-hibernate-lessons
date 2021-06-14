package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.model.Employee_;
import com.example.demo.model.Skill;
import com.example.demo.model.Skill_;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.criteria.*;
import java.util.List;

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


        getAllEmployee(session);
        getEmployeeByName(session);
        getEmployeeBySkill(session);
        getNameAndSkill(session);

        session.getTransaction().commit();
        session.close();
    }

    private void getNameAndSkill(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = criteriaBuilder.createQuery(Object[].class);
        Root<Skill> skill = criteria.from(Skill.class);
        Join<Skill, Employee> employee = skill.join(Skill_.employee, JoinType.LEFT);

        criteria
                .multiselect(employee.get(Employee_.NAME), skill.get(Skill_.skillName))
                .orderBy(criteriaBuilder.asc(employee.get(Employee_.name)));
        List<Object[]> resultList = session.createQuery(criteria).getResultList();
        resultList.forEach(s -> System.out.println(s[0] + " - " + s[1]));
    }

    private void getAllEmployee(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        criteriaQuery.from(Employee.class);

        List<Employee> employeeList = session.createQuery(criteriaQuery).getResultList();
        employeeList.forEach(System.out::println);
    }

    private void getEmployeeBySkill(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Skill> skill = criteriaQuery.from(Skill.class);
        Join<Skill, Employee> employee = skill.join(Skill_.employee);
        criteriaQuery.select(employee).where(criteriaBuilder.equal(skill.get(Skill_.skillName), "persuasiveness"));
        List<Employee> resultList = session.createQuery(criteriaQuery).getResultList();
        resultList.forEach(System.out::println);
    }

    private void getEmployeeByName(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        // тот класс, от которого начинаем "поход" по join
        Root<Employee> employee = criteriaQuery.from(Employee.class);
        // небезопаный запрос
//        criteriaQuery.select(employee).where(criteriaBuilder.equal(employee.get("name"), "Alex"));
        criteriaQuery.select(employee).where(criteriaBuilder.equal(employee.get(Employee_.name), "Alex"));
        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
    }

}
