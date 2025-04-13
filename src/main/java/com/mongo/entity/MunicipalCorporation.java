package com.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "municipal_corporation")
public class MunicipalCorporation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "_id",
            nullable = false,
            columnDefinition = "ObjectId"
    )
    private String id;

    @Field("municipal_corporation_name")
    @TextIndexed
    private String municipalCorporationName;

    @Field("municipal_commissioner")
    private String municipalCommissioner;
}
