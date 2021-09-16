import java.util.*;

public class DualNumber {
    public enum DualType {
        ARABIC,
        ROMAN
    }

    private final int number;
    private final DualType type;

    public DualNumber(String line) {
        Scanner scanner = new Scanner(line);
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
            type = DualType.ARABIC;
        } else {
            if((number = parseRoman(line.trim())) < 1)
                throw new CalculatorWrongFormatException(line);
            type = DualType.ROMAN;
        }
        scanner.close();
    }

    public DualNumber(int number, DualType type) {
        this.number = number;
        this.type = type;
    }

    //TODO: Как в одной строчке инициализировать в обратном порядке одновременно с объявлением?
    static Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
    static {
        map.putAll(Map.ofEntries(
                Map.entry(1000, "M"),
                Map.entry(900, "CM"),
                Map.entry(500, "D"),
                Map.entry(400, "CD"),
                Map.entry(100, "C"),
                Map.entry(90, "XC"),
                Map.entry(50, "L"),
                Map.entry(40, "XL"),
                Map.entry(10, "X"),
                Map.entry(9, "IX"),
                Map.entry(5, "V"),
                Map.entry(4, "IV"),
                Map.entry(1, "I")
        ));
    }

    @Override
    public String toString() {
        if (type == DualType.ARABIC)
            return Integer.toString(number);
        else {
            //Romans have only natural
            if (number < 1)
                throw new CalculatorRomanNotNaturalException(number);
            StringBuilder result = new StringBuilder();
            int counter = number;
            Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
            while (counter > 0) {
                Map.Entry<Integer, String> current = iterator.next();
                while (counter >= current.getKey()) {
                    counter -= current.getKey();
                    result.append(current.getValue());
                }
            }
            return result.toString();
        }
    }

    public int getNumber() {
        return number;
    }

    public DualType getType() {
        return type;
    }

    public boolean equalsType(DualNumber another) {
        return (this.type.equals(another.getType()));
    }

    /*
    Compute value of roman number and return it in int type
    Return 0 if line has no symbols
    Return -1 if line has not roman number symbols
    */
    private static int parseRoman(String line) {
        int current, buffer = 0;
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case 'I':
                    current = 1;
                    break;
                case 'V':
                    current = 5;
                    break;
                case 'X':
                    current = 10;
                    break;
                case 'L':
                    current = 50;
                    break;
                case 'C':
                    current = 100;
                    break;
                case 'D':
                    current = 500;
                    break;
                case 'M':
                    current = 1000;
                    break;
                default:
                    return (-1);
            }
            if (current > buffer)
                buffer = current - buffer;
            else
                buffer += current;
        }
        return (buffer);
    }
}
