import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CountriesTest {
    /*
      Given
        User connects to the database

      When
        User sends the query to get the region ids from "countries" table

      Then
        Verify that the number of region ids greater than 1 is 17.

      And
        User closes the connection
     */
    @Test
    public void countryTest(){
        //User connects to the database
        JdbcUtils.connectToDatabase("localhost","postgres","postgres","4168263009htc");
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        List<Object> region_ids= JdbcUtils.getColumnList("region_id","countries");
        System.out.println("region_ids = " + region_ids);

        //Assert that the number of region ids greater than 1 is 17.
        int idsGreaterThan1 = region_ids.stream().filter(t->(Integer)t>1).collect(Collectors.toList()).size();
        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);
        assertEquals(17,idsGreaterThan1);

        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();

    }
}
/*
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>postGreJDBC</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.5.1</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.9.1</version>
            <scope>test</scope>
        </dependency>


    </dependencies>

</project>
 */