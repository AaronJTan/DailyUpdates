import { getCP24LatestNews } from "../../services/NewsService.js"
import { cp24Category } from "./categories";
import LinkContainer from "../LinksContainer";
import ExternalLink from "../ExternalLink";

export default async function CP24News({ category }) {
    let error = false;
    category = category || cp24Category.LATESTNEWS;
    let headlines;

    try {
        headlines = await getCP24LatestNews(category.path);
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