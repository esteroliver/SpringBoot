package com.esteroliver.restimages.profilepic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, UUID> {
}
