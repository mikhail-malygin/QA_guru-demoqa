package tests;

import org.junit.jupiter.api.*;

public class JUnit5Examples {

    @BeforeAll
    static void beforeAll() {
        System.out.println("== Something before all tests");
    }

    @BeforeAll
    static void beforeAll1() {
        System.out.println("== Something 1 before all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("==== Something before every tests");
    }

    @Test
    @Disabled
    void firstTest() {
        System.out.println("======== Started first test");
    }

    @Test
    @Disabled
    void secondTest() {
        System.out.println("======== Started second test");
    }

    @AfterEach
    void afterEach() {
        System.out.println("================ Something after every tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("================================ Something after all tests");
    }
}
