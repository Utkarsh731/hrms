package com.incs83.hrms.repository;

import com.incs83.hrms.entity.Passcode;
import org.springframework.data.repository.CrudRepository;

public interface PasscodeRepository extends CrudRepository<Passcode,String> {
Passcode findByCode(String code);

}
