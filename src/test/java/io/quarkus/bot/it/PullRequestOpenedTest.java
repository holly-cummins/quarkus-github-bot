package io.quarkus.bot.it;

import io.quarkiverse.githubapp.testing.GitHubAppTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHEvent;

import java.io.IOException;

import static io.quarkiverse.githubapp.testing.GitHubAppTesting.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@QuarkusTest
@GitHubAppTest
public class PullRequestOpenedTest {

    @Test
    void titleEndsWithDot() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-ends-with-dot.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should not end up with dot\n" +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleStartsWithLowercase() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-starts-with-lowercase.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should preferably start with an uppercase character (if it makes sense!)\n" +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleStartsWithgRPC() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-starts-with-gRPC.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleContainsIssueNumber() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-contains-issue-number.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should not contain an issue number (use `Fix #1234` in the description instead)\n"
                                    +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleStartsWithFeat() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-starts-with-feat.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should preferably start with an uppercase character (if it makes sense!)\n" +
                                    "- title should not start with chore/docs/feat/fix/refactor but be a proper sentence\n" +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleStartsWithFix() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-starts-with-fix.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should preferably start with an uppercase character (if it makes sense!)\n" +
                                    "- title should not start with chore/docs/feat/fix/refactor but be a proper sentence\n" +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleStartsWithChore() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-starts-with-chore.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should preferably start with an uppercase character (if it makes sense!)\n" +
                                    "- title should not start with chore/docs/feat/fix/refactor but be a proper sentence\n" +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    @Test
    void titleStartsWithDocs() throws IOException {
        when().payloadFromClasspath("/pullrequest-opened-title-starts-with-docs.json")
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {
                    verify(mocks.pullRequest(527350930))
                            .comment("Thanks for your pull request!\n" +
                                    "\n" +
                                    "The title of your pull request does not follow our editorial rules. Could you have a look?\n"
                                    +
                                    "\n" +
                                    "- title should preferably start with an uppercase character (if it makes sense!)\n" +
                                    "- title should not start with chore/docs/feat/fix/refactor but be a proper sentence\n" +
                                    "\n" +
                                    "> This message is automatically generated by a bot.");
                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }
}
