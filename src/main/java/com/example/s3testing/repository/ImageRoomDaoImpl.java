package com.example.s3testing.repository;

import com.example.s3testing.model.dto.ImageRoom;
import com.example.s3testing.repository.dao.ImageRoomDao;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRoomDaoImpl implements ImageRoomDao {

    // 그냥 setter만 넣어도 작동은 함.
    //    @Setter(onMethod_ = {@Autowired})
    @Setter
    private SqlSession sql;

    // 매퍼
    // namespace는 mapper에 명시해둔 namespace와 동일해야하며, dao interface 파일의 경로임. 확장자 제외
    private String namespace = "com.example.s3testing.repository.dao.ImageRoomDao";

    @Override
    public int insertImage(ImageRoom imageRoom) {
        //  namespace 뒤에 . 이 필요한 이유는 경로 뒤에 mapper에서 설정한 id가 호출되도록 설정하는 것이기 떄문
        // . 뒤에는 현재 메서드 명을 넣는다.
        return sql.insert(namespace + ".insertImage", imageRoom);
    }

    @Override
    public ImageRoom getImage(String fileName) {
        System.out.println("dao impl의 fileName = " + fileName);
        return sql.selectOne(namespace + ".getImage", fileName);
    }
}
