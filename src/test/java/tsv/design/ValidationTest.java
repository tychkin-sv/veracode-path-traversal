package tsv.design;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import tsv.design.exception.SecurityTraversalException;
import tsv.design.model.Folders;
import tsv.design.validation.Validation;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @ParameterizedTest
    @CsvSource({
            "'validFolder', 'validFolder', 'validFolder'",
            "'test_folder', 'test_folder', 'test_folder'"
    })
    void testValidateFolder(String folderToValidate, String folder, String expectedResult) {
        Validation validation = new Validation();
        Folders folders = Folders.builder().data(Map.of(folder, folder)).build();
        String result = validation.validateFolder(folderToValidate, folders);
        assertEquals(expectedResult, result, "Valid folder should be returned");
    }

    @Test
    void testValidateFolder_ValidArchiveFolder() {
        Validation validation = new Validation();
        String validFolder = "2024-09-16 05:51:29.030385222_example";
        String result = validation.validateFileName(validFolder);
        assertEquals(validFolder, result, "Valid folder should be returned");
    }

    @Test
    void testValidateFolderThrowsExceptionForInvalidFolder() {
        Validation validation = new Validation();
        Folders folders = Folders.builder().data(Map.of("validFolder", "validFolder")).build();

        Exception exception = assertThrows(SecurityTraversalException.class, () -> {
            validation.validateFolder("invalidFolder", folders);
        });

        String expectedMessage = "Potential Path Traversal: invalidFolder is not a valid folder";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}