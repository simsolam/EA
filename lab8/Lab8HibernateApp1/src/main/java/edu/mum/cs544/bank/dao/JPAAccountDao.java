package edu.mum.cs544.bank.dao;

import edu.mum.cs544.bank.EntityManagerHelper;
import edu.mum.cs544.bank.domain.Account;
import edu.mum.cs544.bank.domain.Customer;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.Collection;

public class JPAAccountDao implements IAccountDAO{
    @Override
    public void saveAccount(Account account) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.merge(account);
    }

    @Override
    public Account loadAccount(long accountnumber) {
        EntityManager em = EntityManagerHelper.getCurrent();
        return em.find(Account.class, accountnumber);
    }

    @Override
    public Collection<Account> getAccounts() {
        EntityManager em = EntityManagerHelper.getCurrent();
//        return em.createQuery("from Account", Account.class).getResultList();
        return em.createQuery("select distinct a from Account a join fetch a.entryList", Account.class).getResultList();
    }
}
