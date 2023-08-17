import LinkContainer from "./LinksContainer";
import ExternalLink from "./ExternalLink";
import { newsService } from "@/services/NewsService";
import { cp24Category } from "@/config/cp24Categories";

export default async function CP24News({ category }) {
    let error = false;
    category = category || cp24Category.LATESTNEWS;
    let headlines;

    try {
        headlines = await newsService.getCP24LatestNews(category.path);
    } catch (err) {
        error = true;
    }

    return (
        <LinkContainer error={error} siteName={`CP24 ${category.title}`}>
            {
                headlines?.data.map((headline, index) =>
                    <ExternalLink key={index} href={headline.url}>
                        {headline.headline}
                    </ExternalLink>
                )
            }
        </LinkContainer>
    );
}