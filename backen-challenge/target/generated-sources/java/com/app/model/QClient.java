package com.app.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClient is a Querydsl query type for Client
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClient extends EntityPathBase<Client> {

    private static final long serialVersionUID = -1907205552L;

    public static final QClient client = new QClient("client");

    public final StringPath code = createString("code");

    public final StringPath company = createString("company");

    public final BooleanPath encrypt = createBoolean("encrypt");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final BooleanPath male = createBoolean("male");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QClient(String variable) {
        super(Client.class, forVariable(variable));
    }

    public QClient(Path<? extends Client> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClient(PathMetadata metadata) {
        super(Client.class, metadata);
    }

}

