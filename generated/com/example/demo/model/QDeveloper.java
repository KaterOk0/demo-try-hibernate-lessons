package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeveloper is a Querydsl query type for Developer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeveloper extends EntityPathBase<Developer> {

    private static final long serialVersionUID = -1823897493L;

    public static final QDeveloper developer = new QDeveloper("developer");

    public final QEmployee _super = new QEmployee(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    public final EnumPath<ProgrammingLanguage> programmingLanguage = createEnum("programmingLanguage", ProgrammingLanguage.class);

    //inherited
    public final SetPath<Skill, QSkill> skills = _super.skills;

    public QDeveloper(String variable) {
        super(Developer.class, forVariable(variable));
    }

    public QDeveloper(Path<? extends Developer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeveloper(PathMetadata metadata) {
        super(Developer.class, metadata);
    }

}

