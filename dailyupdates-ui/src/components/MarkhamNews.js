import { getMarkhamNews } from "../services/NewsService.js"
import ExternalLink from "./ExternalLink.js";
import LinkContainer from "./LinksContainer.js";

export default async function MarkhamNews() {
    let error = false;
    let headlines;

    try {
        headlines = await getMarkhamNews();
    } catch (err) {
        error = true;
    }

    return (
        <LinkContainer error={error} siteName="Markham News">
            {
                headlines?.map((headline, index) =>
                    <ExternalLink key={index} href={headline.link}>
                        {headline.headline}
                    </ExternalLink>
                )
            }
        </LinkContainer>
    );
}