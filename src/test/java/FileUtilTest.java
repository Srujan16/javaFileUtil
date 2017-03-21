import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wavemaker.tutorial.fileUtil.utils.FileUtil;
import com.wavemaker.tutorial.fileUtil.utils.JsonUtil;

/**
 * Created by srujant on 22/2/17.
 */
public class FileUtilTest {

    ClassLoader classLoader;

    @Before
    public void getClassLoader() {
        classLoader = getClass().getClassLoader();
    }

    @Test
    public void copyFileWithBuffer() {
        File inputFile = new File(classLoader.getResource("Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf").getFile());
        FileUtil.copyFileWithBuffer(inputFile, "/home/srujant/Documents/Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf");
    }

    @Test
    public void copyFileWithOutBuffer() {
        File inputFile = new File(classLoader.getResource("Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf").getFile());
        FileUtil.copyFileWithoutBuffer(inputFile, "/home/srujant/Documents/Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf");
    }

    @Test
    public void copyFileWithFileUtil() {
        File inputFile = new File(classLoader.getResource("Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf").getFile());
        FileUtil.fileCopyWithFileUtil(inputFile, "/home/srujant/Documents/Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf");

    }

    @Test
    public void copyFileWithFileChannels() {
        File inputFile = new File(classLoader.getResource("Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf").getFile());
//        File inputFile = new File("/home/srujant/multipartPart2");
        FileUtil.fileCopyWithChannels(inputFile, "/home/srujant/Documents/multipartPart2");
    }

    @Test
    public void testReadJson() {
        InputStream inputStream = classLoader.getResourceAsStream("employees");
        JsonUtil.readJsonFromInputStream(inputStream);
    }

    @Test
    public void createZip() {
        File file1 = new File(classLoader.getResource("Head_First_Design_Patterns_Elisabeth_Freeman_Eric_Freeman(www.ebook-dl.com).pdf").getFile());
        File file2 = new File(classLoader.getResource("RequestLifecycle.png").getFile());
        File file3 = new File(classLoader.getResource("wavemaker").getFile());
        File file4 = new File("/home/srujant/multipartPart2");
        File[] files = new File[1];
        List<File> fileList = new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        FileUtil.createZip(fileList.toArray(files), "/home/srujant/Documents/customZip");
    }


}
