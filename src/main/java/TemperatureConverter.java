public class TemperatureConverter {

    /**
     * 将华氏温度转换为摄氏温度
     * 公式: Celsius = (Fahrenheit - 32) * 5 / 9
     * @param fahrenheit 华氏温度
     * @return 摄氏温度
     */
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    /**
     * 将摄氏温度转换为华氏温度
     * 公式: Fahrenheit = (Celsius * 9 / 5) + 32
     * @param celsius 摄氏温度
     * @return 华氏温度
     */
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    /**
     * 检查给定的摄氏温度是否为极端温度
     * 如果温度低于-40°C或高于50°C，则视为极端温度
     * @param celsius 摄氏温度
     * @return 如果是极端温度返回true，否则返回false
     */
    public boolean isExtremeTemperature(double celsius) {
        if (celsius < -40) {
            return true;
        }
        if (celsius > 50) {
            return true;
        }
        return false;
    }

    /**
     * 将开氏温度转换为摄氏温度
     * 公式: Celsius = Kelvin - 273.15
     * @param kelvin 开氏温度
     * @return 摄氏温度
     */
    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    /**
     * 主方法，用于演示温度转换功能
     */
    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();

        System.out.println("=== Temperature Converter Demo ===");
        System.out.println();

        // 测试华氏温度转摄氏温度
        double fahrenheit = 100.0;
        double celsius = converter.fahrenheitToCelsius(fahrenheit);
        System.out.println("Fahrenheit to Celsius:");
        System.out.println(fahrenheit + "°F = " + String.format("%.2f", celsius) + "°C");
        System.out.println();

        // 测试摄氏温度转华氏温度
        double celsius2 = 25.0;
        double fahrenheit2 = converter.celsiusToFahrenheit(celsius2);
        System.out.println("Celsius to Fahrenheit:");
        System.out.println(celsius2 + "°C = " + String.format("%.2f", fahrenheit2) + "°F");
        System.out.println();

        // 测试开氏温度转摄氏温度
        double kelvin = 300.0;
        double celsiusFromKelvin = converter.kelvinToCelsius(kelvin);
        System.out.println("Kelvin to Celsius:");
        System.out.println(kelvin + "K = " + String.format("%.2f", celsiusFromKelvin) + "°C");
        System.out.println();

        // 测试极端温度检查
        double tempToCheck = 60.0;
        boolean isExtreme = converter.isExtremeTemperature(tempToCheck);
        System.out.println("Temperature Check:");
        System.out.println(tempToCheck + "°C is " + (isExtreme ? "EXTREME" : "NORMAL"));
        System.out.println();

        System.out.println("=== Conversion Complete ===");
    }
}
