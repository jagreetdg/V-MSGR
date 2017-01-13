package server_resources;

/**
 *
 * @author Jagreet Das Gupta
 */
public interface ClientListener
{
    void signIn(String userName);
    void signOut(String userNamme);
    void clientStatus(String status);
}
