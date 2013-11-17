// http://developer.github.com/

public class GitHubApi {

    public static void main(String[] args) {
        Get get = new Get();

        // User info
        System.out.println("USER INFO:");
        String userInfo = get.executeGet("https://api.github.com/users/mubinov");
        userInfo = userInfo.replaceAll(",", ",\n");
        System.out.println(userInfo);

        // repos info
        System.out.println("REPOS INFO:");
        String reposInfo = get.executeGet("https://api.github.com/repos/mubinov/itis");
        reposInfo = reposInfo.replaceAll(",", ",\n");
        System.out.println(reposInfo);

        // commits info
        System.out.println("COMMITS LIST:");
        String commits = get.executeGet("https://api.github.com/repos/mubinov/itis/commits");
        commits = commits.replaceAll(",", ",\n");
        System.out.println(commits);

    }
}
