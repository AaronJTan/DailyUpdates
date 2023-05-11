import CP24News from "@/components/CP24/CP24News";
import { cp24Category } from "@/components/CP24/categories";
import MarkhamNews from "@/components/MarkhamNews/MarkhamNews";
import RedFlagDeals from "@/components/RedFlagDeals/RedFlagDeals";
import TheHackerNews from "@/components/TheHackerNews/TheHackerNews";

export default function Home() {

  return (
    <div className="grid gap-2 grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
      <RedFlagDeals />
      <MarkhamNews />
      <TheHackerNews />
      <CP24News category={cp24Category.LATESTNEWS} />
      <CP24News category={cp24Category.WORLDNEWS} />
    </div>
  )
}
