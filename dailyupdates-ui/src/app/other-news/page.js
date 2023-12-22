import CP24News from "@/components/CP24News"
import MarkhamNews from "@/components/MarkhamNews"
import TheHackerNews from "@/components/TheHackerNews"
import { cp24Category } from "@/config/cp24Categories"

export default function RedFlagDealsPage() {

  return (
    <div className="container grid gap-2 grid-cols-2">
      <div className="col-span-full lg:col-span-1">
        <MarkhamNews />
      </div>
      <div className="col-span-full lg:col-span-1">
        <CP24News category={cp24Category.LATESTNEWS} />
      </div>
      <div className="col-span-full lg:col-span-1">
        <CP24News category={cp24Category.WORLDNEWS} />
      </div>
      <div className="col-span-full lg:col-span-1">
        <TheHackerNews />
      </div>
    </div>
  )
}