package junit5Test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author xieziwei99
 * 2019-09-27
 */
@DisplayName("我的第一个测试用例")
public class FirstTest {

    @BeforeAll
    // 所修饰方法必须void，必须static，必须不是private
    public static void init() {
        System.out.println("Initialize...");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("Clean data...");
    }

    @BeforeEach
    public void tearUp() {
        System.out.println("The test method starts...");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("The test method ends...");
    }

    @Test   // must not be private or static
    @DisplayName("第一个测试")
        // 甚至不需要public？
    void printFirstTest() {
        System.out.println("我的第一个测试开始测试");
    }

    @Test
    @DisplayName("第二个测试")
    @Disabled
    void printSecondTest() {
        System.out.println("我的第二个测试开始测试");
    }

    @DisplayName("自定义名称重复测试")
    @RepeatedTest(value = 3, name = "{displayName} 第{currentRepetition}次")
    public void printRepeatedTest() {
        System.out.println("do repeated test");
    }

    @Test
    public void assertionsTest() {
        int[] nums = {0, 1, 2, 3, 4};
        Assertions.assertEquals(nums[1], 1);

        Assertions.assertAll("numbers",     // heading有什么用吗
                () -> Assertions.assertEquals(nums[0], 0),
                () -> Assertions.assertEquals(nums[1], 1),
                () -> Assertions.assertEquals(nums[3], 3));

        Assertions.assertTimeoutPreemptively(Duration.of(1, ChronoUnit.SECONDS),
                () -> Thread.sleep(500));

        Assertions.assertThrows(ArithmeticException.class,
                () -> System.out.println(5 / nums[0]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"apple", "age", "ace"})
    public void paramsTest(String s) {
        Assertions.assertTrue(s.startsWith("a"));
    }

    @ParameterizedTest
    @CsvSource({"a, apple", "b, ball", "c, cat"})
    public void csvSourceTest(String k, String v) {
        Assertions.assertTrue(v.startsWith(k));
    }

    @Nested
    @DisplayName("内嵌测试类")
    class NestTest {
        @Test
        void printNestTest() {
            System.out.println("内嵌测试类执行测试");
        }
    }
}
