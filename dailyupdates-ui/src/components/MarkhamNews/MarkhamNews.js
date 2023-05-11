import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getMarkhamNews} from "../../services/NewsService.js"

export default async function MarkhamNews() {
    const headlines = await getMarkhamNews();

    const Headline = ({headline}) => {
        return (
            <tr className="card-body-tr">
                <td>
                    <a href={headline.link} target="_blank">
                        {headline.headline}
                    </a>
                </td>
            </tr>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                Markham News
            </CardTitle>
            <CardBody>
                <table className="table-auto">
                    <tbody>
                        {headlines.map((headline) => 
                            <Headline headline={headline} />
                        )}
                    </tbody>
                </table>
            </CardBody>
        </Card>
    );
}