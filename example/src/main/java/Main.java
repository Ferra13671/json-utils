import com.ferra13671.jsonutils.model.JElement;
import com.ferra13671.jsonutils.model.JFormat;
import com.ferra13671.jsonutils.model.JModel;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@UtilityClass
public class Main {

    public void main(String[] args) {
        test("jsonExample.json", JFormat.JSON);
        test("json5Example.json5", JFormat.JSON5);
        test("hjsonExample.hjson", JFormat.HJSON);
    }

    public void test(String name, JFormat format) {
        try(
                InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(name);
                Reader reader = new InputStreamReader(inputStream)
        ) {
            JElement element = JModel.read(reader, format);

            System.out.println(JModel.write(element, format));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
