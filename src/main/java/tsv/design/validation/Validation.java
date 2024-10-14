package tsv.design.validation;

import org.owasp.encoder.Encode;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import tsv.design.exception.SecurityTraversalException;
import tsv.design.model.Folders;

@Slf4j
public class Validation {

    private static final String POTENTIAL_PATH_TRAVERSAL = "Potential Path Traversal: ";
    // CWE ID 73 - https://www.veracode.com/security/java/cwe-73
    // https://community.veracode.com/s/article/how-do-i-fix-cwe-73-external-control-of-file-name-or-path-in-java
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9 :._/\\\\-]+");
    private static final String IS_NOT_A_VALID_FILENAME = " is not a valid filename";

    public String validateFolder(String folder, Folders folders) {
        if (!folders.getData().containsKey(folder) && !"".equals(folder)) {
            throw new SecurityTraversalException(
                    POTENTIAL_PATH_TRAVERSAL + folder + " is not a valid folder");
        }
        return folder;
    }

    public String validateFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new SecurityTraversalException(
                    POTENTIAL_PATH_TRAVERSAL + fileName + IS_NOT_A_VALID_FILENAME);
        }
        if (fileName.contains("..") || fileName.startsWith("//") || !VALID_NAME_PATTERN.matcher(fileName).matches()) {
            throw new SecurityTraversalException(
                    POTENTIAL_PATH_TRAVERSAL + fileName + IS_NOT_A_VALID_FILENAME);
        }

        // CWE ID 117 - https://www.veracode.com/security/java/cwe-117
        log.info("fileName: {}", Encode.forJava(fileName));
        return fileName;
    }

    @SuppressWarnings("java:S107")
    private String recreateString(String value) {
        return value.chars().mapToObj(Character::toString).collect(Collectors.joining());
    }

}
