package junit.basic;

import com.example.thuctap.ThuctapApplication;
import com.example.thuctap.dto.RoleDTO;
import com.example.thuctap.entity.Role;
import com.example.thuctap.repository.RoleRepository;
import com.example.thuctap.service.RoleService;
import com.example.thuctap.service.impl.RoleServiceImpl;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK ,classes = ThuctapApplication.class)
public class RoleTestIntegration {

//    @InjectMocks
//    RoleServiceImpl roleService;
//
//    @Mock
//    private RoleRepository roleRepository;

    @Autowired
    RoleService roleService;



    @Test
    public void create() {
        //Khi minh test có thể thêm vào db hoặc không.
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ROLE_ADMIN");
        roleService.create(roleDTO);

        RoleDTO saveRole = roleService.getById(roleDTO.getId());
        assertThat(roleDTO.getName()).isEqualTo(saveRole.getName());

    }


    public void update(RoleDTO roleDTO) {

    }


    public void delete(int id) {

    }
//    public List<RoleDTO> getAll() {
//
//    }


    public RoleDTO getById(int id) {
        return null;
    }


}
