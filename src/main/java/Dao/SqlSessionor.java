package Dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName 抛出sqlsession
 * @Description
 * @Author:liyunzhi
 * @Date
 * @Version 1.0
 **/
public class SqlSessionor {
    // public static SqlSession sqlSess() throws IOException {
    //     String resource = "mybatis-config.xml";
    //     InputStream inputStream = Resources.getResourceAsStream(resource);
    //     SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //     //2.获取sqlsession对象，执行SQL语句
    //     SqlSession sqlSession = sqlSessionFactory.openSession(true);
    //     return sqlSession;
    // }
}
