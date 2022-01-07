package ir.bontec.programmingchallenge.resources;

import ir.bontec.programmingchallenge.base.mapper.BaseMapper;
import ir.bontec.programmingchallenge.entities.User;
import ir.bontec.programmingchallenge.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
