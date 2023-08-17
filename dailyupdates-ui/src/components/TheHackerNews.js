import { newsService } from "@/services/NewsService";
import LinkContainer from "./LinksContainer";

export default async function TheHackerNews() {
    let error = false;
    let headlines;

    try {
        headlines = await newsService.getTheHackerNewsArticles();
    } catch (err) {
        error = true;
    }

    return (
        <LinkContainer error={error} siteName="The Hacker News">
            {headlines?.data.map((headline, index) =>
                <ExternalLink key={index} href={headline.url}>
                    {headline.headline}
                </ExternalLink>
            )}
        </LinkContainer>
    );
}