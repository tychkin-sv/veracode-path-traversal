package tsv.design.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Folders {

    /**
     * The mapping data.
     */
    @Builder.Default
    private final Map<String, String> data = new HashMap<>();
}
