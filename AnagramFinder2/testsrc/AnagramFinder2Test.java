import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnagramFinder2Test {

	@Test
    public void testSortString()
    {
		AnagramFinder2 classUnderTest = new AnagramFinder2(); 
		String strToSort = "stop";
		String expectedValue = "opst";        
		String sortedAnagram = classUnderTest.sortString(strToSort);

        assertTrue(expectedValue.equals(sortedAnagram));
    }

	@Test
    public void testCreateDictValue()
    {
		AnagramFinder2 classUnderTest = new AnagramFinder2(); 
        String currentValue = "stop";
        String newValue = "tops";
        String resultString = classUnderTest.CreateDictValue(currentValue, newValue);

        assertTrue(resultString.equals("stop,tops"));
    }	
	

}
