package hello.demo.repository;

import hello.demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//JdbcTemplate을 사용하면 반복 코드를 없앨 수 있다, query는 직접 작성
public class JdbcTemplateMemberRepository implements MemberRepository{

    // 인젝션을 받을 수는 없다
    private final JdbcTemplate jdbcTemplate;

    // 생성자가 하나만 있으면 Autowried를 생략할 수 있다
//     @Autowired
    // dataSource를 받는다
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // SimpleJdbcInsert : 쿼리를 짜지 않고 테이블 명과 키 값을 통해서 sql문을 만들어준다
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // usingGeneratedKeyColumns : 아이디가 자동으로 입력되도록 함(입력받는 것이 아니라), auto_increment를 통해 생성된 값이, "컬럼명"에 자동으로 입력
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        // executeAndReturnKey : key 값을 받는다,  insert를 실행후에, 자동 생성된 값(auto_increment) 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 나오는 곳을 rowWaraper로 warpiing해줘야 한다
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        // 병렬 스트림인 경우에는 findAny() 메소드를 사용해야만 정확한 연산 결과를 반환할 수 있습니다
        return result.stream().findAny(); // Optional로 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 결과가 나오는 곳을 rowWaraper로 warpiing해줘야 한다
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        // 병렬 스트림인 경우에는 findAny() 메소드를 사용해야만 정확한 연산 결과를 반환할 수 있습니다
        return result.stream().findAny(); // Optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        // option + enter -> 람다식으로 변경
        return (RowMapper<Member>) (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
