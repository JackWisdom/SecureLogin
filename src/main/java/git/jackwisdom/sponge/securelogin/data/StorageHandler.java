package git.jackwisdom.sponge.securelogin.data;

//@ImplementedBy(FileStorage.class)
public interface StorageHandler {
    public Account loadAccount(String name);

    public boolean hasRecord(String name);

    public void save(Account account);
}
