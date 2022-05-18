package Dao;

import mapper.userinfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import user.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName 抛出mapper对象
 * @Description
 * @Author:liyunzhi
 * @Date
 * @Version 1.0
 **/
public class MybatisMapper {

    public static userinfoMapper mybatisor() throws IOException {
        // User user1 = new User();
        // user1.setUsername("liyi");
        // user1.setPassword("123456");
        // 1.加载mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // //2.获取sqlsession对象，执行SQL语句
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //SqlSession sqlSession = SqlSessionor.sqlSess();
        //3.执行sql   名称空间.id
        // List<User> users = sqlSession.selectList("test.selectAll");
        // System.out.println(users);
        userinfoMapper usermapper =sqlSession.getMapper(userinfoMapper.class);
        // usermapper.insertOne(user1);
        // List<User> users = usermapper.selectAll();
        // System.out.println(users);
        //  //提交事务
        // // sqlSession.commit();
        //  //4.释放资源
        //sqlSession.close();
        return usermapper;
    }
}
