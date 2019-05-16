package edu.eci.arsw.alexandria;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import org.assertj.core.util.diff.Delta;

import java.util.Arrays;
import java.util.List;

public class tempMain {
    public static void main(String args []) throws DiffException, PatchFailedException {

        List<String> original = Arrays.asList("Hola mundo!!!", "This is the second line .", "And here is the finish.");
        List<String> revised = Arrays.asList("Hola mundo 2.", "fake second line","This is the second line.", "end", "x");

        Patch<String> patch = DiffUtils.diff(original, revised);

        //simple output the computed patch to console
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.out.println(delta);
        }

        //generating diff information.
        Patch<String> diff = DiffUtils.diff(original, revised);

//generating unified diff format
        List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff("original-file.txt", "new-file.txt", original, diff, 0);

        unifiedDiff.forEach(System.out::println);

//importing unified diff format from file or here from memory to a Patch
        Patch<String> importedPatch = UnifiedDiffUtils.parseUnifiedDiff(unifiedDiff);

//apply patch to original list
        List<String> patchedText = DiffUtils.patch(original, importedPatch);

        System.out.println(patchedText);
    }
}
