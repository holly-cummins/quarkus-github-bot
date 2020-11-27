package io.quarkus.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.kohsuke.github.GHEventPayload;
import org.kohsuke.github.GHPullRequest;

import io.quarkiverse.githubapp.event.PullRequest;
import io.quarkus.bot.config.QuarkusBotConfig;

class CheckPullRequestEditorialRules {

    private static final Logger LOG = Logger.getLogger(CheckPullRequestEditorialRules.class);

    private static final Pattern SPACE_PATTERN = Pattern.compile("\\s+");
    private static final Pattern ISSUE_PATTERN = Pattern.compile("#[0-9]+");
    private static final Pattern FIX_FEAT_CHORE = Pattern.compile("^(fix|chore|feat|docs)[(:]");

    @Inject
    QuarkusBotConfig quarkusBotConfig;

    void checkPullRequestEditorialRules(@PullRequest.Opened GHEventPayload.PullRequest pullRequestPayload) throws IOException {
        GHPullRequest pullRequest = pullRequestPayload.getPullRequest();
        String title = pullRequest.getTitle();

        List<String> errorMessages = getErrorMessages(title);

        if (errorMessages.isEmpty()) {
            return;
        }

        StringBuilder comment = new StringBuilder("Thanks for your pull request!\n\n" +
                "The title of your pull request does not follow our editorial rules. Could you have a look?\n\n");
        for (String errorMessage : errorMessages) {
            comment.append(errorMessage).append("\n");
        }
        comment.append("\n> This message is automatically generated by a bot.");

        if (!quarkusBotConfig.isDryRun()) {
            pullRequest.comment(comment.toString());
        } else {
            LOG.info("Pull request #" + pullRequest.getNumber() + " - Add comment " + comment.toString());
        }
    }

    private static List<String> getErrorMessages(String title) {
        List<String> errorMessages = new ArrayList<>();

        if (title == null || title.isEmpty()) {
            return Collections.singletonList("title should not be empty");
        }

        if (title.endsWith(".")) {
            errorMessages.add("title should not end up with dot");
        }
        if (title.endsWith("…")) {
            errorMessages.add("title should not end up with ellipsis (make sure the title is complete)");
        }
        if (SPACE_PATTERN.split(title.trim()).length < 2) {
            errorMessages.add("title should count at least 2 words to describe the change properly");
        }
        if (!Character.isUpperCase(title.codePointAt(0))) {
            errorMessages.add("title should preferably start with an uppercase character (if it makes sense!)");
        }
        if (ISSUE_PATTERN.matcher(title).matches()) {
            errorMessages.add("title should not contain an issue number (use 'Fix #1234' in the description instead)");
        }
        if (FIX_FEAT_CHORE.matcher(title).matches()) {
            errorMessages.add("title should not start with chore/feat/fix/docs but be a proper sentence");
        }

        return errorMessages;
    }
}
