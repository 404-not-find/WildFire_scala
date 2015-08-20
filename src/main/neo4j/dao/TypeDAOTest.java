package dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TypeDAOTest {

    @Test
    public void test() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("1", "huha");
        data.put("2", "jia");
        data.put("3", "guo");
        data.put("4", "nian");
        TypeDAO dao = new TypeDAO();

        System.out.println(dao.createObjectData(data));
        System.out.println(dao.getDataById(2));

        //System.out.println(dao.getBranch(192, new String[]{"huhu"}, true, 3));
        // System.out.println(dao.getDataRelations(192, new String[]{"huhu"}, true));
        //System.out.println(dao.getDataById(192));
        // System.out.println(dao.createObjectLink(192, 195, "huhu", data));
    }
}
