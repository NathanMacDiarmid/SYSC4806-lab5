package com.example.lab5;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab5Application {

	private static final Logger log = LoggerFactory.getLogger(Lab5Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lab5Application.class, args);
	}

	@Bean
	public CommandLineRunner buddies(BuddyInfoRepository repository) {
		return (args) -> {
			repository.save(new BuddyInfo("Bob Dylan", "123456789", 1));
			repository.save(new BuddyInfo("Dylan Bob", "987654321", 2));
			repository.save(new BuddyInfo("Elvis Presley", "123789456", 3));
			repository.save(new BuddyInfo("Presley Elvis", "789123456", 4));

			log.info("");
			log.info("Buddies found with findAll():");
			log.info("-------------------------------");
			for (BuddyInfo buddy : repository.findAll()) {
				log.info(buddy.getID() + " " + buddy.getName() + " " + buddy.getNumber());
			}
			log.info("");

			log.info("Buddy found with findById(2):");
			log.info("-------------------------------");
			BuddyInfo buddy = repository.findById(2);
			log.info(buddy.getID() + " " + buddy.getName() + " " + buddy.getNumber());
			log.info("");

			log.info("Buddy found with findByName(Elvis Presley):");
			log.info("-------------------------------");
			BuddyInfo buddy2 = repository.findByName("Elvis Presley");
			log.info(buddy2.getID() + " " + buddy2.getName() + " " + buddy2.getNumber());
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner books(AddressBookRepository repository) {
		return (args) -> {
			BuddyInfo buddy1 = new BuddyInfo("Bob Dylan", "123456789", 1);
			BuddyInfo buddy2 = new BuddyInfo("Dylan Bob", "987654321", 2);
			BuddyInfo buddy3 = new BuddyInfo("Elvis Presley", "123789456", 3);
			BuddyInfo buddy4 = new BuddyInfo("Presley Elvis", "789123456", 4);
			BuddyInfo buddy5 = new BuddyInfo("Post Malone", "123789456", 5);
			BuddyInfo buddy6 = new BuddyInfo("Malone Post", "789123456", 6);
			
			ArrayList<BuddyInfo> list = new ArrayList<>();
			ArrayList<BuddyInfo> list2 = new ArrayList<>();
			list.add(buddy1);
			list.add(buddy2);
			list.add(buddy3);
			list.add(buddy4);
			list2.add(buddy5);
			list2.add(buddy6);

			repository.save(new AddressBook(list, 1));
			repository.save(new AddressBook(list2, 2));

			log.info("");
			log.info("Books found with findAll():");
			log.info("-------------------------------");
			for (AddressBook book : repository.findAll()) {
				log.info("Book " + book.getID());
			}
			log.info("");

			log.info("Buddies in AddressBook found with findById(1):");
			log.info("-------------------------------");
			AddressBook book = repository.findById(1);
			for (BuddyInfo buddy : book.getBuddies()) {
				log.info(buddy.getID() + " " + buddy.getName() + " " + buddy.getNumber());
			}
			log.info("");

			log.info("Buddies in AddressBook found with findById(2):");
			log.info("-------------------------------");
			AddressBook book2 = repository.findById(2);
			for (BuddyInfo buddy : book2.getBuddies()) {
				log.info(buddy.getID() + " " + buddy.getName() + " " + buddy.getNumber());
			}
			log.info("");
		};
	}

}
