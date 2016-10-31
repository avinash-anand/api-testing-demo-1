package utils;

import com.fasterxml.jackson.databind.JsonNode;
import rest.DatePatterns;

public class TypeUtils implements DatePatterns {

    public static boolean isOfType(JsonNode node, String type) {
        switch (type) {
            case "boolean": return node.isBoolean();
            case "int": return node.isInt();
            case "string": return node.isTextual();
            case "date": return isValidDatePattern(node, datePattern);
            case "datetime": return isValidDateTimePattern(node, dateTimePattern);
            case "double": return node.isDouble();
            case "object": return node.isContainerNode();
            case "null": return (null == node || node.isNull());
            default: throw new RuntimeException("Undefined Type");
        }
    }

    private static boolean isValidDatePattern(JsonNode node, String pattern) {
        String dateString = node.asText();
        return dateString.matches(pattern);
    }

    private static boolean isValidDateTimePattern(JsonNode node, String pattern) {
        String datetimeString = node.asText();
        return datetimeString.matches(pattern);
    }

}
