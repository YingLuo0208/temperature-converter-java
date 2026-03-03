import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TemperatureConverter类的测试类
 */
class TemperatureConverterTest {

    private TemperatureConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TemperatureConverter();
    }

    @Test
    void testFahrenheitToCelsius() {
        // 测试冰点: 32°F = 0°C
        assertEquals(0.0, converter.fahrenheitToCelsius(32), 0.001);

        // 测试沸点: 212°F = 100°C
        assertEquals(100.0, converter.fahrenheitToCelsius(212), 0.001);

        // 测试负温度: -40°F = -40°C
        assertEquals(-40.0, converter.fahrenheitToCelsius(-40), 0.001);

        // 测试室温: 68°F = 20°C
        assertEquals(20.0, converter.fahrenheitToCelsius(68), 0.001);

        // 测试人体温度: 98.6°F = 37°C
        assertEquals(37.0, converter.fahrenheitToCelsius(98.6), 0.001);

        // 测试极低温度: -459.67°F = -273.15°C (绝对零度)
        assertEquals(-273.15, converter.fahrenheitToCelsius(-459.67), 0.01);

        // 测试零度华氏: 0°F ≈ -17.78°C
        assertEquals(-17.778, converter.fahrenheitToCelsius(0), 0.001);
    }

    @Test
    void testCelsiusToFahrenheit() {
        // 测试冰点: 0°C = 32°F
        assertEquals(32.0, converter.celsiusToFahrenheit(0), 0.001);

        // 测试沸点: 100°C = 212°F
        assertEquals(212.0, converter.celsiusToFahrenheit(100), 0.001);

        // 测试负温度: -40°C = -40°F
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40), 0.001);

        // 测试室温: 20°C = 68°F
        assertEquals(68.0, converter.celsiusToFahrenheit(20), 0.001);

        // 测试人体温度: 37°C = 98.6°F
        assertEquals(98.6, converter.celsiusToFahrenheit(37), 0.001);

        // 测试极低温度: -273.15°C = -459.67°F (绝对零度)
        assertEquals(-459.67, converter.celsiusToFahrenheit(-273.15), 0.01);

        // 测试舒适温度: 25°C = 77°F
        assertEquals(77.0, converter.celsiusToFahrenheit(25), 0.001);

        // 测试高温: 50°C = 122°F
        assertEquals(122.0, converter.celsiusToFahrenheit(50), 0.001);
    }

    @Test
    void testIsExtremeTemperature() {
        // 测试极端低温
        assertTrue(converter.isExtremeTemperature(-41), "温度 -41°C 应该被认为是极端温度");
        assertTrue(converter.isExtremeTemperature(-50), "温度 -50°C 应该被认为是极端温度");
        assertTrue(converter.isExtremeTemperature(-100), "温度 -100°C 应该被认为是极端温度");

        // 测试极端高温
        assertTrue(converter.isExtremeTemperature(51), "温度 51°C 应该被认为是极端温度");
        assertTrue(converter.isExtremeTemperature(60), "温度 60°C 应该被认为是极端温度");
        assertTrue(converter.isExtremeTemperature(100), "温度 100°C 应该被认为是极端温度");

        // 测试边界值（应该不是极端温度）
        assertFalse(converter.isExtremeTemperature(-40), "温度 -40°C 是边界值，不应该被认为是极端温度");
        assertFalse(converter.isExtremeTemperature(50), "温度 50°C 是边界值，不应该被认为是极端温度");

        // 测试正常温度范围
        assertFalse(converter.isExtremeTemperature(0), "温度 0°C 应该是正常温度");
        assertFalse(converter.isExtremeTemperature(25), "温度 25°C 应该是正常温度");
        assertFalse(converter.isExtremeTemperature(-20), "温度 -20°C 应该是正常温度");
        assertFalse(converter.isExtremeTemperature(37), "温度 37°C 应该是正常温度");
        assertFalse(converter.isExtremeTemperature(-39), "温度 -39°C 应该是正常温度");
        assertFalse(converter.isExtremeTemperature(49), "温度 49°C 应该是正常温度");

        // 测试临界值附近
        assertTrue(converter.isExtremeTemperature(-40.01), "温度 -40.01°C 应该被认为是极端温度");
        assertTrue(converter.isExtremeTemperature(50.01), "温度 50.01°C 应该被认为是极端温度");
        assertFalse(converter.isExtremeTemperature(-39.99), "温度 -39.99°C 不应该被认为是极端温度");
        assertFalse(converter.isExtremeTemperature(49.99), "温度 49.99°C 不应该被认为是极端温度");
    }

    @Test
    void testRoundTripConversion() {
        // 测试往返转换的准确性：摄氏 -> 华氏 -> 摄氏
        double[] celsiusValues = {0, 25, -40, 100, 37, -20, 50};
        for (double celsius : celsiusValues) {
            double fahrenheit = converter.celsiusToFahrenheit(celsius);
            double backToCelsius = converter.fahrenheitToCelsius(fahrenheit);
            assertEquals(celsius, backToCelsius, 0.001,
                "往返转换 " + celsius + "°C 应该返回原始值");
        }

        // 测试往返转换的准确性：华氏 -> 摄氏 -> 华氏
        double[] fahrenheitValues = {32, 68, -40, 212, 98.6, 0, 122};
        for (double fahrenheit : fahrenheitValues) {
            double celsius = converter.fahrenheitToCelsius(fahrenheit);
            double backToFahrenheit = converter.celsiusToFahrenheit(celsius);
            assertEquals(fahrenheit, backToFahrenheit, 0.001,
                "往返转换 " + fahrenheit + "°F 应该返回原始值");
        }
    }

    @Test
    void testExtremeTemperatureWithConversion() {
        // 测试将华氏极端温度转换为摄氏后，检查是否识别为极端温度

        // -40°F = -40°C (边界值，不是极端温度)
        double celsius1 = converter.fahrenheitToCelsius(-40);
        assertFalse(converter.isExtremeTemperature(celsius1));

        // -50°F ≈ -45.56°C (极端温度)
        double celsius2 = converter.fahrenheitToCelsius(-50);
        assertTrue(converter.isExtremeTemperature(celsius2));

        // 122°F = 50°C (边界值，不是极端温度)
        double celsius3 = converter.fahrenheitToCelsius(122);
        assertFalse(converter.isExtremeTemperature(celsius3));

        // 130°F ≈ 54.44°C (极端温度)
        double celsius4 = converter.fahrenheitToCelsius(130);
        assertTrue(converter.isExtremeTemperature(celsius4));
    }

    @Test
    void testNegativeZeroAndPositiveZero() {
        // 测试负零和正零
        assertEquals(32.0, converter.celsiusToFahrenheit(0.0), 0.001);
        assertEquals(32.0, converter.celsiusToFahrenheit(-0.0), 0.001);
        assertEquals(0.0, converter.fahrenheitToCelsius(32.0), 0.001);
    }

    @Test
    void testKelvinToCelsius() {
        // 测试示例: 300 K = 26.85°C
        assertEquals(26.85, converter.kelvinToCelsius(300), 0.001);

        // 测试绝对零度: 0 K = -273.15°C
        assertEquals(-273.15, converter.kelvinToCelsius(0), 0.001);

        // 测试水的冰点: 273.15 K = 0°C
        assertEquals(0.0, converter.kelvinToCelsius(273.15), 0.001);

        // 测试水的沸点: 373.15 K = 100°C
        assertEquals(100.0, converter.kelvinToCelsius(373.15), 0.001);

        // 测试室温: 293.15 K = 20°C
        assertEquals(20.0, converter.kelvinToCelsius(293.15), 0.001);

        // 测试人体温度: 310.15 K = 37°C
        assertEquals(37.0, converter.kelvinToCelsius(310.15), 0.001);

        // 测试极端高温: 500 K = 226.85°C
        assertEquals(226.85, converter.kelvinToCelsius(500), 0.001);
    }
}
